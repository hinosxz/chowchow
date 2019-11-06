import React from 'react';
import { Divider, Typography } from 'antd';

import { Like } from 'lib/types';
import { ShowItem } from 'components/show-list/ShowItem/ShowItem';
import { api } from 'lib/api/api';
import { Placeholder } from 'components/ui/Placeholder/Placeholder';

const BLOCK = 'show-list_page-show-list';
const { Title } = Typography;

export const PageShowList: React.FunctionComponent = () => {
  const [data, setData] = React.useState<Like[]>([]);
  const [isLoading, setIsLoading] = React.useState(false);

  React.useEffect(() => {
    setIsLoading(true);
    api.get('users/likes').json<Like[]>().then(like => {
      setData(like);
      setIsLoading(false);
    });
  }, []);

  return (
    <div className={BLOCK}>
      <Title level={2}>My favorite shows</Title>
      <Divider />
      {isLoading ? <Placeholder /> : data.map(like => <ShowItem key={like.show.id} like={like} />)}
    </div>
  );
};
