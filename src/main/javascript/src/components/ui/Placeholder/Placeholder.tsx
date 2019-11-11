import React from 'react';
import { Spin } from 'antd';

import './placeholder.scss';

const BLOCK = 'ui_placeholder';

export const Placeholder: React.FunctionComponent = () => <div className={BLOCK}><Spin size="large" /></div>;
