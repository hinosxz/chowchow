import React from 'react';

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
