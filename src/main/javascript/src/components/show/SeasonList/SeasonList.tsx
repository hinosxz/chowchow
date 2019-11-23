import React from 'react';
import { List, Skeleton } from 'antd';

import { parseDate } from 'lib/util';
import { Season } from 'lib/types';

interface SeasonListProps {
  season: Season | null;
}

export const SeasonList: React.FunctionComponent<SeasonListProps> = ({ season }) => (
  !season
    ? <Skeleton />
    : (
      <List
        itemLayout="horizontal"
        dataSource={season.episodes}
        renderItem={episode => {
          const airDate = parseDate(episode.air_date).toLocaleDateString();
          return (
            <List.Item>
              <List.Item.Meta
                title={`${episode.season_number}x${episode.episode_number}, ${episode.name}, Airs on: ${airDate}`}
                description={episode.overview}
              />
            </List.Item>
          );
        }}
      />
    ));
