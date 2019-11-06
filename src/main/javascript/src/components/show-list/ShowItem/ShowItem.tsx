import React from 'react';
import {
  Button, Col, Divider, Icon, Row, Typography,
} from 'antd';
import { Link } from 'react-router-dom';

import { Like } from 'lib/types';
import { Mark } from 'components/ui/Mark/Mark';

import './show-item.scss';

const BLOCK = 'show-list_show-item';
const { Paragraph, Title } = Typography;

interface ShowItemProps {
  like: Like;
}

export const ShowItem: React.FunctionComponent<ShowItemProps> = ({ like }) => (
  <>
    <Row className={BLOCK} gutter={[32, 16]}>
      <Col className={`${BLOCK}__poster`} span={4}>
        <img className={`${BLOCK}__poster__image`} src={like.show.poster_path} alt="Poster" />
      </Col>
      <Col className={`${BLOCK}__description`} span={20}>
        <Title level={3}>{like.show.name}</Title>
        <Mark mark={like.mark} />
        <Paragraph>{like.show.overview}</Paragraph>
        <div className={`${BLOCK}__go-to-button`}>
          <Link to={`shows/${like.show.id}`}>
            <Button type="primary">
              Go to show
              <Icon type="right" />
            </Button>
          </Link>
        </div>
      </Col>
    </Row>
    <Divider />
  </>
);
