import React from 'react';
import { Divider, Typography } from 'antd';

import { Like } from 'lib/types';
import { ShowItem } from 'components/show-list/ShowItem/ShowItem';
import { api } from 'lib/api/api';

const BLOCK = 'show-list_page-show-list';
const { Title } = Typography;

export const PageShowList: React.FunctionComponent = () => {
  const [data, setData] = React.useState<Like[]>([]);

  React.useEffect(() => {
    api.get('users/likes').json<Like[]>().then(like => setData(like));
  }, []);

  return (
    <div className={BLOCK}>
      <Title level={2}>My favorite shows</Title>
      <Divider />
      {data.map(like => <ShowItem key={like.show.id} like={like} />)}
    </div>
  );
};
