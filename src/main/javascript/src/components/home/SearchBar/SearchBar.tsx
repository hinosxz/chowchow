import React from 'react';
import Async from 'react-select/async';

import { getSearch } from 'lib/api/search';
import { SearchShow } from 'lib/types';

function loadOptions(name: string) {
  return getSearch(name).then(res => res.results.map(show => ({
    label: show.name,
    value: show,
  })));
}

export interface OptionType {
  label: string;
  value: SearchShow;
}

interface SearchBarProps {
  setValue: (value: OptionType) => void;
  value: OptionType | null;
}

export const SearchBar: React.FunctionComponent<SearchBarProps> = ({
  setValue,
  value,
}) => (
  <Async<OptionType>
    cacheOptions
    defaultOptions={[]}
    loadOptions={loadOptions}
    noOptionsMessage={() => null}
    onChange={option => setValue(option as OptionType)}
    placeholder="Search shows..."
    value={value}
  />
);
