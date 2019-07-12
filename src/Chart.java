import java.util.List;

public class Chart {
	
		private String date;
		private String time;
		private String priceOpen;
		private String priceHigh;
		private String priceLow;
		private String priceClose;
		private String volume;
		
		private static String chartName;

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getPriceOpen() {
			return priceOpen;
		}

		public void setPriceOpen(String priceOpen) {
			this.priceOpen = priceOpen;
		}

		public String getPriceHigh() {
			return priceHigh;
		}

		public void setPriceHigh(String priceHigh) {
			this.priceHigh = priceHigh;
		}

		public String getPriceLow() {
			return priceLow;
		}

		public void setPriceLow(String priceLow) {
			this.priceLow = priceLow;
		}
		
		public String getPriceClose() {
			return priceClose;
		}

		public void setPriceClose(String priceClose) {
			this.priceClose = priceClose;
		}
		public String getVolume() {
			return volume;
		}

		public void setVolume(String volume) {
			this.volume = volume;
		}

		
		public Object createObjectChartList(String quotName) throws Exception {

			 @SuppressWarnings("unchecked")
			Class<List<Chart>> clazz = (Class<List<Chart>>) Class.forName(quotName);
			   Object result = clazz.newInstance(); 
			//   List<Chart> test = (List<Chart>) result; 
			   return result;
			}
	}
