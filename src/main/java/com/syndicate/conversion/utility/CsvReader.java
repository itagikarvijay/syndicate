/**
 * 
 */
package com.syndicate.conversion.utility;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

import com.syndicate.master.product.ProductDTO;
import com.syndicate.master.product.UploadProductDTO;

/**
 * @author Vijay
 *
 */
public class CsvReader {

	public static CsvReaderResponse csvToProducts(InputStream is) {
		CsvReaderResponse csvReaderResponse = new CsvReaderResponse();

		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<CSVRecord> csvReader = csvParser.getRecords();
			for (CSVRecord h : csvReader) {
				if (validateCsvRow(h)) {
					UploadProductDTO productDTO = new UploadProductDTO(h.get("product"), h.get("uom"),
							h.get("category"), h.get("hsncode"), h.get("qty"), h.get("rate"), h.get("gst"),
							h.get("cgst"), h.get("sgst"));
					csvReaderResponse.getFailuerList().add(productDTO);
				} else {
					UploadProductDTO productDTO = new UploadProductDTO(h.get("product"), h.get("uom"),
							h.get("category"), h.get("hsncode"), h.get("qty"), h.get("rate"), h.get("gst"),
							h.get("cgst"), h.get("sgst"));
					csvReaderResponse.getSuccessList().add(productDTO);
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			csvReaderResponse.setErrorMessage(e.getMessage());
		}

		return csvReaderResponse;
	}

	private static boolean validateCsvRow(CSVRecord h) {
		boolean blnValue = false;
		try {
			if (StringUtils.isBlank(h.get("product"))) {
				blnValue = true;
			}
			if (StringUtils.isBlank(h.get("uom"))) {
				blnValue = true;
			}
			if (StringUtils.isBlank(h.get("category"))) {
				blnValue = true;
			}
			if (StringUtils.isBlank(h.get("hsncode"))) {
				blnValue = true;
			}
			if (StringUtils.isBlank(h.get("qty"))) {
				blnValue = true;
				Float.parseFloat(h.get("qty"));
			}
			if (StringUtils.isBlank(h.get("rate"))) {
				blnValue = true;
				Float.parseFloat(h.get("rate"));
			}
			if (StringUtils.isBlank(h.get("gst"))) {
				blnValue = true;
				Float.parseFloat(h.get("gst"));
			}
			if (StringUtils.isBlank(h.get("cgst"))) {
				blnValue = true;
				Float.parseFloat(h.get("cgst"));
			}
			if (StringUtils.isBlank(h.get("sgst"))) {
				blnValue = true;
				Float.parseFloat(h.get("sgst"));
			}
		} catch (NumberFormatException e) {
			blnValue = true;
		} catch (NullPointerException n) {
			blnValue = true;
		}
		return blnValue;
	}

}
