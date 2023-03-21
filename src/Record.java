public class Record {
    final double[] dims;
    final String name;
    final int length;

    public Record(String line) {
        String[] values = line.replace(",", ".").strip().split("\t");
        length = values.length - 1;
        dims = new double[length];
        for (int i = 0; i < length; i++) {
            dims[i] = Double.parseDouble(values[i].strip());
        }
        name = values[length].strip();
    }

    public double distance(Record record) {
        double sum = 0;
        for (int i = 0; i < length; i++) {
            double diff = dims[i] - record.dims[i];
            sum += diff * diff;
        }
        return sum;
    }
}