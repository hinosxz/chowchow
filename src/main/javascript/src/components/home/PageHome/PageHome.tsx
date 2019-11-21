import React from 'react';
import { Divider, Typography } from 'antd';

import { OptionType, SearchBar } from 'components/home/SearchBar/SearchBar';
import { Alerts } from 'components/home/Alerts/Alerts';
import { ShowViewContainer } from 'components/home/ShowView/ShowViewContainer';

const { Title } = Typography;

export const PageHome: React.FunctionComponent = () => {
  const [selectedShow, setSelectedShow] = React.useState<OptionType | null>(
    null,
  );
  return (
    <>
      <Title level={2}>Alerts</Title>
      <Alerts />
      <Divider />
      <Title level={2}>Search</Title>
      <SearchBar value={selectedShow} setValue={setSelectedShow} />
      <Divider />
      {selectedShow && selectedShow.value && (
        <ShowViewContainer show={selectedShow.value} />
      )}
    </>
  );
};
