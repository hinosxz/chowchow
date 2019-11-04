import React from 'react';
import { useParams } from 'react-router-dom';

export const PageShow: React.FunctionComponent = () => {
  const { id } = useParams();
  return (
    <div>
      Show:
      {' '}
      {id}
    </div>
  );
};
