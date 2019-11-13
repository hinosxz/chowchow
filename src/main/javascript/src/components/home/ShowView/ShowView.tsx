import React from 'react';
import {
  Button,
  Card, Col, Descriptions, Icon, Row, Typography,
} from 'antd';

import { SearchShow } from 'lib/types';
import { parseDate } from 'lib/util';
import { postLikes } from 'lib/api/likes';

const { Item } = Descriptions;
const { Paragraph } = Typography;

interface ShowViewProps {
  show: SearchShow;
}

export const ShowView: React.FunctionComponent<ShowViewProps> = ({ show }) => {
  const [isLikeDisabled, setIsLikeDisabled] = React.useState(false);

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

        <Button disabled={isLikeDisabled} type="primary" onClick={() => postLikes(show.id).then(setIsLikeDisabled)}>
          Like
          <Icon type="like" />
        </Button>
      </Col>
    </Row>
  );
};
