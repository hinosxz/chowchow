import React from 'react';
import {
  Alert, Button, Form, Icon, Input,
} from 'antd';
import { FormComponentProps } from 'antd/es/form';

import { postRegister } from 'lib/api/register';

const BLOCK = 'authentication_login-form';

type RegistrationFormProps = FormComponentProps<{username: string, password: string}>;

export const RegistrationForm = Form.create<RegistrationFormProps>({ name: 'login_form' })(
  ({ form }: RegistrationFormProps) => {
    const [error, setError] = React.useState();
    const [isSuccess, setIsSuccess] = React.useState();

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
            postRegister(username, password)
              .then(
                res => {
                  if (res) {
                    setIsSuccess(true);
                    setError(false);
                  } else {
                    setIsSuccess(false);
                    setError('Cannot register with this username/password');
                  }
                },
              )
              .catch(setError);
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
          <Button type="primary" htmlType="submit" block>
            Sign up
          </Button>
        </Form.Item>
        <Form.Item>
          {isSuccess && (
          <Alert
            message="You're successfully registered in our database. Please login using the above tab."
            type="success"
          />
          )}
          {error && (
          <Alert
            message="Couldn't register this user in our database. Please check that your username/password is correct or retry in a few seconds."
            type="error"
          />
          )}
        </Form.Item>
      </Form>
    );
  },
);
