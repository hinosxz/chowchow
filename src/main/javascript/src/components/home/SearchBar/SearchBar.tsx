import React from 'react';

import { getSearch } from 'lib/api/search';

export const SearchBar: React.FunctionComponent = () => {
  React.useEffect(() => {
    getSearch('12 monkeys').then(res => {
      console.log(res);
    });
  }, []);
  return null;
};
