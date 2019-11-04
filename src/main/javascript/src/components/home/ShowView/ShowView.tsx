import React from 'react';
import {
  Card, Col, Descriptions, Row, Typography,
} from 'antd';

import { SearchShow } from 'lib/types';

const { Item } = Descriptions;
const { Paragraph } = Typography;

interface ShowViewProps {
  show: SearchShow;
}

export const ShowView: React.FunctionComponent<ShowViewProps> = ({ show }) => {
  const [year, month, day] = show.firstAirDate;
  const firstAirDate = new Date(year, month, day).toLocaleDateString();
  const originCountries = show.originCountry.join(', ');
  return (
    <Row gutter={[32, 16]}>
      <Col span={8}>
        <Card bodyStyle={{ padding: 0 }} cover={<img alt="Poster" src={show.posterPath} />} title={show.name} />
      </Col>
      <Col span={16}>
        <Descriptions title="Show Summary">
          <Item label="First Air Date">{firstAirDate}</Item>
          <Item label="Popularity">{show.popularity}</Item>
          <Item label="Vote Average">{show.voteAverage}</Item>
          <Item label="Original Language">{show.originalLanguage}</Item>
          <Item label="Origin Countries">{originCountries}</Item>
        </Descriptions>

        <Descriptions title="Overview">
          <Paragraph>
            {show.overview}
          </Paragraph>
        </Descriptions>
      </Col>
    </Row>
  );
};
