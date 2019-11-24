import React from 'react';
import { Alert, List } from 'antd';

import { Placeholder } from 'components/ui/Placeholder/Placeholder';
import { Alert as ShowAlert } from 'lib/types';
import { useGetData } from 'lib/hooks';
import { parseDate } from 'lib/util';

export const Alerts: React.FunctionComponent = () => {
  const [data, setData] = React.useState<ShowAlert[] | null>([]);
  const [isLoading, setIsLoading] = React.useState(false);
  const [error, setError] = React.useState<any>(null);
  useGetData<ShowAlert[]>('alerts', setData, setIsLoading, setError);

  if (isLoading) {
    return <Placeholder />;
  }

  if (error) {
    return (
      <Alert
        message="Could not retrieve broadcasting alerts. Please retry in a few seconds."
        type="error"
      />
    );
  }

  return (
    <Alert
      message={
        !data || data.length === 0 ? (
          'None of your favorite shows have upcoming episodes'
        ) : (
          <List
            itemLayout="horizontal"
            dataSource={data}
            renderItem={alert => {
              const { episode } = alert;
              const airDate = parseDate(episode.air_date).toLocaleDateString();
              return (
                <List.Item>
                  <List.Item.Meta
                    title={`${alert.show_name}: ${episode.season_number}x${episode.episode_number}, ${episode.name}, Airs on: ${airDate}`}
                    description={episode.overview}
                  />
                </List.Item>
              );
            }}
          />
        )
      }
      type="info"
    />
  );
};
