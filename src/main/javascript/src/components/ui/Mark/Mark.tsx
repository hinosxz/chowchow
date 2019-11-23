import React from 'react';

import { BoneIcon } from 'components/ui/BoneIcon/BoneIcon';
import './mark.scss';

const BLOCK = 'ui_mark';

interface MarkProps {
  mark: 0 | 1 | 2 | null;
}

export const Mark: React.FunctionComponent<MarkProps> = ({ mark }) => (
  <div className={BLOCK}>
    <BoneIcon className={`${BLOCK}__icon`} theme={typeof mark === 'number' && mark >= 0 ? 'filled' : undefined} />
    <BoneIcon className={`${BLOCK}__icon`} theme={typeof mark === 'number' && mark >= 1 ? 'filled' : undefined} />
    <BoneIcon className={`${BLOCK}__icon`} theme={typeof mark === 'number' && mark >= 2 ? 'filled' : undefined} />
  </div>
);
