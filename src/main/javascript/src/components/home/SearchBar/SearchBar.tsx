import React from 'react';
import Async from 'react-select/async';

import { getSearch } from 'lib/api/search';

function loadOptions(name: string) {
  return getSearch(name).then(res => res.results.map(show => ({
    label: show.name,
    value: show,
  })));
}

export const SearchBar: React.FunctionComponent = () => (
  <Async
    cacheOptions
    defaultOptions={[]}
    loadOptions={loadOptions}
    noOptionsMessage={() => null}
    placeholder="Search shows..."
  />
);
