import React from 'react';
import { Alert, Divider, Typography } from 'antd';

import { Like } from 'lib/types';
import { ShowItem } from 'components/show-list/ShowItem/ShowItem';
import { Placeholder } from 'components/ui/Placeholder/Placeholder';
import { useGetData } from 'lib/hooks';

const BLOCK = 'show-list_page-show-list';
const { Title } = Typography;

export const PageShowList: React.FunctionComponent = () => {
  const [data, setData] = React.useState<Like[] | null>([]);
  const [isLoading, setIsLoading] = React.useState(false);
  const [error, setError] = React.useState<any>(null);
  useGetData<Like[]>('likes', setData, setIsLoading, setError);

  return (
    <div className={BLOCK}>
      <Title level={2}>My watch list</Title>
      <Divider />
      {error && (
        <Alert
          message="Could not retrieve your watch list. Please retry in a few seconds."
          type="error"
        />
      )}
      {isLoading ? (
        <Placeholder />
      ) : (
        <>
          {(data || []).map(like => (
            <ShowItem key={like.show.id} like={like} />
          ))}
          {data && data.length === 0 && (
            <Alert message="You don't have any liked show yet!" type="info" />
          )}
        </>
      )}
    </div>
  );
};
