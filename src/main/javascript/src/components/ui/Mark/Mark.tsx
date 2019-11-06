import React from 'react';
import { Icon } from 'antd';

import './mark.scss';

const BLOCK = 'ui_mark';

interface MarkProps {
  mark: 0 | 1 | 2 | null;
}

export const Mark: React.FunctionComponent<MarkProps> = ({ mark }) => (
  <div className={BLOCK}>
    <Icon className={`${BLOCK}__icon`} type="star" theme={mark ? 'filled' : undefined} />
    <Icon className={`${BLOCK}__icon`} type="star" theme={mark && mark > 0 ? 'filled' : undefined} />
    <Icon className={`${BLOCK}__icon`} type="star" theme={mark && mark > 1 ? 'filled' : undefined} />
  </div>
);
