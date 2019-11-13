import React from 'react';
import { useParams, useHistory } from 'react-router-dom';
import { Alert, PageHeader } from 'antd';

import { Like } from 'lib/types';
import { useGetData } from 'lib/hooks';
import { Placeholder } from 'components/ui/Placeholder/Placeholder';
import { RoutePath } from 'lib/constants';

import './page-show.scss';

const BLOCK = 'show_page-show';

export const PageShow: React.FunctionComponent = () => {
  const { id } = useParams();
  const { replace } = useHistory();

  const [like, setLike] = React.useState<Like | null>();
  const [isLoading, setIsLoading] = React.useState(false);
  const [error, setError] = React.useState<any>(null);
  useGetData<Like>(`likes/${id}`, setLike, setIsLoading, setError);

  if (isLoading) {
    return (
      <Placeholder />
    );
  }

  if (error || !like) {
    return (
      <Alert
        message="Could not retrieve the selected show. Please retry in a few seconds."
        type="error"
      />
    );
  }

  const { show } = like;
  return (
    <>
      <PageHeader title={show.name} onBack={() => replace(RoutePath.shows)} />
      <div className={`${BLOCK}__content`} style={{ backgroundImage: `url(${show.backdrop_path})` }} />
    </>
  );
};
