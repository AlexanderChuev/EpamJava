package com.chuyeu.training.myapp.webapp.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.InvalidObjectException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chuyeu.training.myapp.webapp.memorization.JsonData;

public class Serializator {

	private final String path = "src/main/resources/data.data";
	private final Logger LOGGER = LoggerFactory.getLogger(Serializator.class);

	public void serialization(Map<String, JsonData> dataMap) {

		File file = new File(path);
		try (ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(file))) {
			if (ostream != null) {
				ostream.writeObject(dataMap);
			}
		} catch (FileNotFoundException e) {
			LOGGER.error("File with path {} not found / serialization", path);
		} catch (NotSerializableException e) {
			LOGGER.error("Object is not serializable");
		} catch (IOException e) {
			LOGGER.error("IO serialization error");
		}

	}

	@SuppressWarnings("unchecked")
	public Map<String, JsonData> deserialization() throws InvalidObjectException {

		try (ObjectInputStream istream = new ObjectInputStream(new FileInputStream(new File(path)))) {
			if (istream != null) {
				return (Map<String, JsonData>) istream.readObject();
			}

		} catch (ClassNotFoundException e) {
			LOGGER.error("Class Not Found Exception");
		} catch (FileNotFoundException e) {
			LOGGER.error("File with path {} not found / deserialization", path);
		} catch (InvalidClassException e) {
			LOGGER.error("Version classes mismatch");
		} catch (IOException e) {
			LOGGER.error("IO deserialization error");
		}
		throw new InvalidObjectException("Exception! The object is NOT restored!");
	}
}
