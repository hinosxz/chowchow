import * as React from 'react';
import { useHistory, useLocation } from 'react-router-dom';
import {
  Button, Form, Icon, Input,
} from 'antd';
import { FormComponentProps } from 'antd/es/form';

import { useAuth } from 'context/authentication';
import { postLogin } from 'lib/api/login';
import { RoutePath } from 'lib/constants';

import './login-form.scss';

const BLOCK = 'authentication_login-form';

type LoginFormProps = FormComponentProps<{username: string, password: string}>;

export const LoginForm = Form.create<LoginFormProps>({ name: 'login_form' })(({ form }: LoginFormProps) => {
  const { setIsAuthenticated } = useAuth();
  const { replace } = useHistory();
  const { state: locationState } = useLocation();

  const { from } = locationState || { from: { pathname: RoutePath.home } };

  if (!form) {
    return null;
  }
  const { getFieldDecorator } = form;

  const handleSubmit: React.FormEventHandler<HTMLFormElement> = event => {
    event.preventDefault();
    if (form) {
      form.validateFields((err, values) => {
        if (!err) {
          const { username, password } = values;
          postLogin(username, password)
            .then(
              () => {
                setIsAuthenticated(true);
                replace(from);
              },
            );
        }
      });
    }
  };

  return (
    <Form onSubmit={handleSubmit} className={`${BLOCK}`}>
      <Form.Item>
        {getFieldDecorator('username', {
          rules: [{ required: true, message: 'Please enter a username!' }],
        })(
          <Input
            prefix={<Icon type="user" />}
            placeholder="Username"
          />,
        )}
      </Form.Item>
      <Form.Item>
        {getFieldDecorator('password', {
          rules: [{ required: true, message: 'Please enter your password' }],
        })(
          <Input
            prefix={<Icon type="lock" />}
            type="password"
            placeholder="Password"
          />,
        )}
      </Form.Item>
      <Form.Item>
        <Button type="primary" htmlType="submit" className={`${BLOCK}__button`}>
          Log in
        </Button>
        Or
        {' '}
        Register
      </Form.Item>
    </Form>
  );
});
