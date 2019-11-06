import React from 'react';
import { Alert, Divider, Typography } from 'antd';

import { Like } from 'lib/types';
import { ShowItem } from 'components/show-list/ShowItem/ShowItem';
import { api } from 'lib/api/api';
import { Placeholder } from 'components/ui/Placeholder/Placeholder';

const BLOCK = 'show-list_page-show-list';
const { Title } = Typography;

export const PageShowList: React.FunctionComponent = () => {
  const [data, setData] = React.useState<Like[]>([]);
  const [isLoading, setIsLoading] = React.useState(false);
  const [error, setError] = React.useState<any>(null);

  React.useEffect(() => {
    setIsLoading(true);
    api.get('users/likes').json<Like[]>()
      .then(like => {
        setData(like);
        setIsLoading(false);
      })
      .catch(err => {
        setIsLoading(false);
        setError(err);
      });
  }, []);

  return (
    <div className={BLOCK}>
      <Title level={2}>My favorite shows</Title>
      <Divider />
      {error && (
        <Alert
          message="Could not retrieve your favorite shows. Please retry in a few seconds."
          type="error"
        />
      )}
      {isLoading ? <Placeholder /> : data.map(like => <ShowItem key={like.show.id} like={like} />)}
    </div>
  );
};
