export function parseDate(date: [number, number, number]) {
  const [year, month, day] = date;
  const isoString = `${year}-${month}-${day}`;
  return new Date(isoString);
}
