import * as React from 'react';
import {
  Button, Form, Icon, Input,
} from 'antd';
import { FormComponentProps } from 'antd/es/form';

import './login-form.scss';
import { postLogin } from '../../../lib/api/login';
import { AuthenticationState } from '../types';

const BLOCK = 'login-form';

type LoginFormProps = FormComponentProps<{username: string, password: string}>
& {authenticationState: AuthenticationState, setAuthenticationState: (value: AuthenticationState) => void};

export const LoginForm = Form.create<LoginFormProps>({ name: 'login_form' })(({ form, authenticationState, setAuthenticationState }: LoginFormProps) => {
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
              res => {
                if (res) {
                  setAuthenticationState({ isAuthenticated: true });
                }
                console.log(authenticationState);
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
          rules: [{ required: true, message: 'Please input your username!' }],
        })(
          <Input
            prefix={<Icon type="user" />}
            placeholder="Username"
          />,
        )}
      </Form.Item>
      <Form.Item>
        {getFieldDecorator('password', {
          rules: [{ required: true, message: 'Please input your Password!' }],
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
        <a href="/test" className={`${BLOCK}__register`}>Register</a>
      </Form.Item>
    </Form>
  );
});
