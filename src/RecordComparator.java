public record RecordComparator(Record ref) implements java.util.Comparator<Record> {

    @Override
    public int compare(Record o1, Record o2) {
        return Double.compare(ref.distance(o1), ref.distance(o2));
    }
}
