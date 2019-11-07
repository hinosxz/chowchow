import * as React from 'react';
import {
  Button, Form, Icon, Input,
} from 'antd';
import { FormComponentProps } from 'antd/es/form';
import { useHistory } from 'react-router-dom';

import './login-form.scss';
import { postLogin } from '../../../lib/api/login';

const BLOCK = 'login-form';

type LoginFormProps = FormComponentProps<{username: string, password: string}>;

export const LoginForm = Form.create<LoginFormProps>({ name: 'login_form' })(({ form }: LoginFormProps) => {
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
              response => {
                if (response) {
                  const history = useHistory();
                  history.push('/test');
                }
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
