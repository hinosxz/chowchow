import React from 'react';
import { Icon } from 'antd';

import { putLikes } from 'lib/api/likes';

import './clickable-mark.scss';

const BLOCK = 'ui_clickable-mark';

interface ClickableMarkProps {
  mark: 0 | 1 | 2 | null;
  showId: number;
}

export const ClickableMark: React.FunctionComponent<ClickableMarkProps> = ({ mark, showId }) => {
  const [updatedMark, setUpdatedMark] = React.useState<typeof mark>(mark);

  return (
    <div className={BLOCK}>
      <Icon
        className={`${BLOCK}__icon`}
        onClick={() => {
          putLikes(showId, 0).then(res => {
            if (res) {
              setUpdatedMark(0);
            }
          });
        }}
        type="star"
        theme={typeof updatedMark === 'number' && updatedMark >= 0 ? 'filled' : undefined}
      />
      <Icon
        className={`${BLOCK}__icon`}
        onClick={() => {
          putLikes(showId, 1).then(res => {
            if (res) {
              setUpdatedMark(1);
            }
          });
        }}
        type="star"
        theme={typeof updatedMark === 'number' && updatedMark >= 1 ? 'filled' : undefined}
      />
      <Icon
        className={`${BLOCK}__icon`}
        onClick={() => {
          putLikes(showId, 2).then(res => {
            if (res) {
              setUpdatedMark(2);
            }
          });
        }}
        type="star"
        theme={typeof updatedMark === 'number' && updatedMark >= 2 ? 'filled' : undefined}
      />
    </div>
  );
};