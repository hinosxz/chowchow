import React from 'react';
import { useParams, useHistory } from 'react-router-dom';
import {
  Alert, Col, PageHeader, Row, Tag, Typography,
} from 'antd';

import { Like } from 'lib/types';
import { useGetData } from 'lib/hooks';
import { Placeholder } from 'components/ui/Placeholder/Placeholder';
import { RoutePath } from 'lib/constants';
import { parseDate } from 'lib/util';
import { ClickableMark } from 'components/ui/ClickableMark/ClickableMark';

import './page-show.scss';

const BLOCK = 'show_page-show';
const { Title, Text } = Typography;

export const PageShow: React.FunctionComponent = () => {
  const { id } = useParams();
  const { replace } = useHistory();

  const [like, setLike] = React.useState<Like | null>();
  const [isLoading, setIsLoading] = React.useState(false);
  const [error, setError] = React.useState<any>(null);
  useGetData<Like>(`likes/${id}`, setLike, setIsLoading, setError);

  if (isLoading) {
    return (
      <Placeholder />
    );
  }

  if (error || !like) {
    return (
      <Alert
        message="Could not retrieve the selected show. Please retry in a few seconds."
        type="error"
      />
    );
  }

  const { mark, show } = like;
  return (
    <>
      <PageHeader
        title={show.name}
        tags={show.in_production ? <Tag color="green">In Production</Tag> : <Tag color="red">Finished</Tag>}
        onBack={() => replace(RoutePath.shows)}
      />
      <div className={`${BLOCK}__wrapper`} style={{ backgroundImage: `url(${show.backdrop_path})` }}>
        <Row>
          <Col className={`${BLOCK}__content`} span={8}>
            <ClickableMark mark={mark} showId={show.id} />
            <Title level={4}>Created by</Title>
            <Text>{show.created_by.map(creator => creator.name).join(', ')}</Text>
            <Title level={4}>Genres</Title>
            <Text>{show.genres.map(genre => <Tag>{genre.name}</Tag>)}</Text>
            <Title level={4}>Overview</Title>
            <Text>{show.overview}</Text>
            <Title level={4}>Networks</Title>
            <Text>{show.networks.map(network => network.name).join(', ')}</Text>
            <Title level={4}>First Air Date</Title>
            <Text>{parseDate(show.first_air_date).toLocaleDateString()}</Text>
            {show.next_episode_to_air && (
              <>
                <Title level={4}>Next episode</Title>
                <Text>{`${show.next_episode_to_air.season_number}x${show.next_episode_to_air.episode_number}, ${show.next_episode_to_air.name}, airs on: ${parseDate(show.next_episode_to_air.air_date).toLocaleDateString()}`}</Text>
              </>
            )}
          </Col>
        </Row>
      </div>
    </>
  );
};
