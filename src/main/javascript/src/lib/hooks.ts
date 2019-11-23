import React from 'react';

import { api } from 'lib/api/api';

export function useGetData<ResponseType>(
  path: string,
  setData: (data: ResponseType | null) => void,
  setIsLoading: (isLoading: boolean) => void,
  setError: (error: any) => void,
) {
  React.useEffect(() => {
    setIsLoading(true);
    api
      .get(path)
      .json<ResponseType>()
      .then(like => {
        setData(like);
        setIsLoading(false);
        setError(null);
      })
      .catch(err => {
        setData(null);
        setIsLoading(false);
        setError(err);
      });
  }, [path, setData, setError, setIsLoading]);
}

/**
 * Hook that persists a state in the browser's session storage
 * @param storageKey Key that'll be used to retrieve the data in the session storage
 */
export function useBooleanStateWithSessionStorage(
  storageKey: string,
): [boolean, (value: boolean) => void] {
  const sessionValue = sessionStorage.getItem(storageKey) === 'true';
  const [value, setValue] = React.useState(sessionValue);
  React.useEffect(() => {
    sessionStorage.setItem(storageKey, String(value));
  }, [storageKey, value]);
  return [value, setValue];
}
