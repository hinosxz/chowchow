import React from 'react';

import { Like } from 'lib/types';
import { ShowItem } from 'components/show-list/ShowItem/ShowItem';
import { api } from 'lib/api/api';

const BLOCK = 'show-list_page-show-list';

export const PageShowList: React.FunctionComponent = () => {
  const [data, setData] = React.useState<Like[]>([]);

  React.useEffect(() => {
    api.get('users/likes').json<Like[]>().then(like => setData(like));
  }, []);

  return (
    <div className={BLOCK}>
      {data.map(like => <ShowItem key={like.show.id} like={like} />)}
    </div>
  );
};
