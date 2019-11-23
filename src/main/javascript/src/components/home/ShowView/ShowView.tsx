import React from 'react';
import {
  Alert,
  Button,
  Card, Col, Descriptions, Icon, Row, Typography,
} from 'antd';

import { SearchShow } from 'lib/types';
import { parseDate } from 'lib/util';
import { postLikes } from 'lib/api/likes';

import './show-view.scss';

const BLOCK = 'home_show-view';
const { Item } = Descriptions;
const { Paragraph } = Typography;

interface ShowViewProps {
  isLiked: boolean;
  show: SearchShow;
}

export const ShowView: React.FunctionComponent<ShowViewProps> = ({ isLiked, show }) => {
  const [isLikeDisabled, setIsLikeDisabled] = React.useState(isLiked);
  const [error, setError] = React.useState(null);

  const firstAirDate = parseDate(show.first_air_date).toLocaleDateString();
  const originCountries = show.origin_country.join(', ');
  return (
    <Row gutter={[32, 16]}>
      <Col span={8}>
        <Card bodyStyle={{ padding: 0 }} cover={<img alt="Poster" src={show.poster_path} />} title={show.name} />
      </Col>
      <Col span={16}>
        <Descriptions title="Show Summary">
          <Item label="First Air Date">{firstAirDate}</Item>
          <Item label="Popularity">{show.popularity}</Item>
          <Item label="Vote Average">{show.vote_average}</Item>
          <Item label="Original Language">{show.original_language}</Item>
          <Item label="Origin Countries">{originCountries}</Item>
        </Descriptions>

        <Descriptions title="Overview">
          <Paragraph>
            {show.overview}
          </Paragraph>
        </Descriptions>

        <div className={`${BLOCK}__like`}>
          <Button
            disabled={isLikeDisabled}
            type="primary"
            onClick={() => postLikes(show.id).then(() => setIsLikeDisabled(true)).catch(setError)}
          >
            <Icon type="eye" />
            Add to watch list
          </Button>
        </div>

        {error && (
          <Alert
            message="Could not add this show to your watch list. Please retry in a few seconds."
            type="error"
          />
        )}
      </Col>
    </Row>
  );
};
