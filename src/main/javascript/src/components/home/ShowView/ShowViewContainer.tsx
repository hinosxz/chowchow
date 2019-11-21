import React from 'react';

import { SearchShow, Show } from 'lib/types';
import { useGetData } from 'lib/hooks';
import { Placeholder } from 'components/ui/Placeholder/Placeholder';

import { ShowView } from './ShowView';

interface ShowViewContainerProps {
  show: SearchShow;
}

export const ShowViewContainer: React.FunctionComponent<
ShowViewContainerProps
> = ({ show }) => {
  const [data, setData] = React.useState<Show | null>();
  const [isLoading, setIsLoading] = React.useState(false);
  const [, setError] = React.useState<any>(null);
  useGetData<Show>(`likes/${show.id}`, setData, setIsLoading, setError);

  if (isLoading) {
    return <Placeholder />;
  }

  return <ShowView isLiked={Boolean(data)} show={show} />;
};
