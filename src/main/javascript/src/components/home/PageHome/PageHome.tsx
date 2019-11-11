import React from 'react';
import { Divider, Typography } from 'antd';

import { OptionType, SearchBar } from 'components/home/SearchBar/SearchBar';
import { ShowView } from 'components/home/ShowView/ShowView';
import { Alerts } from 'components/home/Alerts/Alerts';

const { Title } = Typography;

export const PageHome: React.FunctionComponent = () => {
  const [selectedShow, setSelectedShow] = React.useState<OptionType | null>(null);
  return (
    <>
      <Title level={2}>Alerts</Title>
      <Alerts />
      <Divider />
      <Title level={2}>Search</Title>
      <SearchBar value={selectedShow} setValue={setSelectedShow} />
      <Divider />
      {selectedShow && selectedShow.value && <ShowView show={selectedShow.value} />}
    </>
  );
};
