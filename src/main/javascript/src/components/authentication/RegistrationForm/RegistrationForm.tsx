import React from 'react';
import {
  Button, Form, Icon, Input, notification,
} from 'antd';
import { FormComponentProps } from 'antd/es/form';

import { postRegister } from 'lib/api/register';

const BLOCK = 'authentication_login-form';

const openSuccessNotification = () => {
  notification.success({
    message: 'Success',
    description:
      "You're successfully registered in our database. Please login using the login tab.",
  });
};

const openErrorNotification = (description: string) => {
  notification.error({
    message: 'Error',
    description,
  });
};

type RegistrationFormProps = FormComponentProps<{
  username: string;
  password: string;
}>;

export const RegistrationForm = Form.create<RegistrationFormProps>({
  name: 'login_form',
})(({ form }: RegistrationFormProps) => {
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
            .then(res => {
              if (res) {
                openSuccessNotification();
              } else {
                openErrorNotification(
                  'This username is already used, please choose another.',
                );
              }
            })
            .catch(() => openErrorNotification(
              "Couldn't register this user at the moment. Please retry in a few seconds.",
            ));
        }
      });
    }
  };

  return (
    <Form onSubmit={handleSubmit} className={`${BLOCK}`}>
      <Form.Item>
        {getFieldDecorator('username', {
          rules: [{ required: true, message: 'Please enter a username!' }],
        })(<Input prefix={<Icon type="user" />} placeholder="Username" />)}
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
    </Form>
  );
});
