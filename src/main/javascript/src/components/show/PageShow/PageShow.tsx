import React from 'react';
import { useParams, useHistory } from 'react-router-dom';
import {
  Alert,
  Button,
  Col,
  Drawer,
  notification,
  PageHeader,
  Popconfirm,
  Row,
  Tag,
  Typography,
} from 'antd';

import { ShowPanel } from 'components/show/ShowPanel/ShowPanel';
import { Placeholder } from 'components/ui/Placeholder/Placeholder';
import { ClickableMark } from 'components/ui/ClickableMark/ClickableMark';
import { deleteLike } from 'lib/api/likes';
import { Like } from 'lib/types';
import { useGetData } from 'lib/hooks';
import { RoutePath } from 'lib/constants';
import { parseDate } from 'lib/util';

import './page-show.scss';

const BLOCK = 'show_page-show';
const { Title, Text } = Typography;

const openErrorNotification = () => {
  notification.error({
    message: 'Server Error',
    description: 'Could not unlike this show at the moment. Please retry in a few seconds.',
  });
};

export const PageShow: React.FunctionComponent = () => {
  const [isDrawerVisible, setIsDrawerVisible] = React.useState(false);

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
            <Text>{show.genres.map(genre => <Tag key={genre.name}>{genre.name}</Tag>)}</Text>
            <Title level={4}>Overview</Title>
            <Text>{show.overview}</Text>
            <Title level={4}>Networks</Title>
            <Text>{show.networks.map(network => network.name).join(', ')}</Text>
            <Title level={4}>First Air Date</Title>
            <Text>{parseDate(show.first_air_date).toLocaleDateString()}</Text>
            {show.next_episode_to_air && (
              <div>
                <Title level={4}>Next episode</Title>
                <Text>{`${show.next_episode_to_air.season_number}x${show.next_episode_to_air.episode_number}, ${show.next_episode_to_air.name}, airs on: ${parseDate(show.next_episode_to_air.air_date).toLocaleDateString()}`}</Text>
              </div>
            )}
            <div className={`${BLOCK}__bottom-buttons`}>
              <Popconfirm
                title="Are you sure you want to unlike this show?"
                onConfirm={() => {
                  deleteLike(show.id)
                    .then(() => replace(RoutePath.shows))
                    .catch(openErrorNotification);
                }}
                okText="Yes"
                cancelText="No"
              >
                <Button icon="delete" type="danger">Unlike</Button>
              </Popconfirm>
              <Button onClick={() => setIsDrawerVisible(true)}>See details</Button>
            </div>
          </Col>
        </Row>
      </div>
      <Drawer
        closable
        onClose={() => setIsDrawerVisible(false)}
        title="Show details"
        visible={isDrawerVisible}
        width="50%"
      >
        <ShowPanel seasons={show.seasons} showId={show.id} />
      </Drawer>
    </>
  );
};
