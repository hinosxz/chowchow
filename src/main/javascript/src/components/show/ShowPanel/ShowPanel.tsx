import React from 'react';
import { Alert, Collapse, Spin } from 'antd';

import { Season, ShowSeason } from 'lib/types';
import { api } from 'lib/api/api';
import { SeasonList } from 'components/show/SeasonList/SeasonList';

const { Panel } = Collapse;

interface ShowPanelProps {
  seasons: ShowSeason[];
  showId: number;
}

export const ShowPanel: React.FunctionComponent<ShowPanelProps> = ({ seasons, showId }) => {
  const [activeKey, setActiveKey] = React.useState();
  const [fetchedSeason, setFetchedSeason] = React.useState<Season | null>(null);
  const [isLoading, setIsLoading] = React.useState(false);
  const [error, setError] = React.useState<any>(null);

  React.useEffect(() => {
    if (activeKey) {
      setFetchedSeason(null);
      setIsLoading(true);
      setError(null);
      api.get(`search/${showId}/seasons/${activeKey}`).json<Season>()
        .then(res => {
          setFetchedSeason(res);
          setIsLoading(false);
          setError(null);
        })
        .catch(err => {
          setFetchedSeason(null);
          setIsLoading(false);
          setError(err);
        });
    }
  }, [activeKey, setError, setIsLoading, showId]);

  return (
    !seasons.length ? (
      <Alert
        message="No seasons for this show yet"
        type="warning"
      />
    ) : (
      <Collapse accordion activeKey={activeKey} onChange={key => setActiveKey(String(key))}>
        {seasons
          .map(season => (
            <Panel
              extra={String(season.season_number) === activeKey && isLoading && (<Spin />)}
              header={season.name}
              key={season.season_number}
            >
              {error ? (
                <Alert
                  message="Could not retrieve the selected season. Please retry in a few seconds."
                  type="error"
                />
              ) : <SeasonList season={fetchedSeason} />}
            </Panel>
          ))}
      </Collapse>
    ));
};
