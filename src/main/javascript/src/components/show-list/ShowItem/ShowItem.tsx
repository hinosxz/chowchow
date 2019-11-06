import React from 'react';
import { Col, Divider, Row } from 'antd';

import { Like } from 'lib/types';

import './show-item.scss';

const BLOCK = 'show-list_show-item';

interface ShowItemProps {
  like: Like;
}

export const ShowItem: React.FunctionComponent<ShowItemProps> = ({ like }) => (
  <>
    <Row className={BLOCK} gutter={[32, 16]}>
      <Col className={`${BLOCK}__poster`} span={4}>
        <img className={`${BLOCK}__poster__image`} src={like.show.posterPath} alt="Poster" />
      </Col>
      <Col className={`${BLOCK}__description`} span={20}>
        {like.show.name}
      </Col>
    </Row>
    <Divider />
  </>
);
