import React from 'react';
import { Divider } from 'antd';

import { OptionType, SearchBar } from 'components/home/SearchBar/SearchBar';
import { ShowView } from 'components/home/ShowView/ShowView';

export const PageHome: React.FunctionComponent = () => {
  const [selectedShow, setSelectedShow] = React.useState<OptionType | null>(null);
  return (
    <>
      <SearchBar value={selectedShow} setValue={setSelectedShow} />
      <Divider />
      {selectedShow && selectedShow.value && <ShowView show={selectedShow.value} />}
    </>
  );
};
