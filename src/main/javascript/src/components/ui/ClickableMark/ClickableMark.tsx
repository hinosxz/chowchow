import React from 'react';

import { putLike } from 'lib/api/likes';
import { BoneIcon } from 'components/ui/BoneIcon/BoneIcon';
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
      <BoneIcon
        className={`${BLOCK}__icon`}
        onClick={() => {
          putLike(showId, 0).then(res => {
            if (res) {
              setUpdatedMark(0);
            }
          });
        }}
        theme={typeof updatedMark === 'number' && updatedMark >= 0 ? 'filled' : undefined}
      />
      <BoneIcon
        className={`${BLOCK}__icon`}
        onClick={() => {
          putLike(showId, 1).then(res => {
            if (res) {
              setUpdatedMark(1);
            }
          });
        }}
        theme={typeof updatedMark === 'number' && updatedMark >= 1 ? 'filled' : undefined}
      />
      <BoneIcon
        className={`${BLOCK}__icon`}
        onClick={() => {
          putLike(showId, 2).then(res => {
            if (res) {
              setUpdatedMark(2);
            }
          });
        }}
        theme={typeof updatedMark === 'number' && updatedMark >= 2 ? 'filled' : undefined}
      />
    </div>
  );
};
