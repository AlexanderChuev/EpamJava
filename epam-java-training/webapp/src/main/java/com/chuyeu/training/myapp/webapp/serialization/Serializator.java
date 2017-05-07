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
	private final String OBJECT_NOT_RESTORED = "Exception! The object is NOT restored!";
	private final String FILE_NOT_FOUND_SERIALIZE = "File not found (serialization)";
	private final String FILE_NOT_FOUND_DESERIALIZE = "File not found (deserialization)";
	private final String FILE_IS_NOT_SERIALIZABLE = "File is not serializable";
	private final String IO_SERIALIZATION_ERROR = "IO serialization error";
	private final String IO_DESERIALIZATION_ERROR = "IO deserialization error";
	private final String THE_FILE_CAN_NOT_BE_CREATE = "the file can not be created";
	private final String VERSION_MISMATCH = "Version mismatch classes";

	public void serialization(Map<String, JsonData> dataMap) {

		File file = new File(path);
		try (ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(file))) {
			if (ostream != null) {
				ostream.writeObject(dataMap);
			}
		} catch (FileNotFoundException e) {
			LOGGER.error(FILE_NOT_FOUND_SERIALIZE);
		} catch (NotSerializableException e) {
			LOGGER.error(FILE_IS_NOT_SERIALIZABLE);
		} catch (IOException e) {
			LOGGER.error(IO_SERIALIZATION_ERROR);
		}

	}

	@SuppressWarnings("unchecked")
	public Map<String, JsonData> deserialization() throws InvalidObjectException {

		try (ObjectInputStream istream = new ObjectInputStream(new FileInputStream(new File(path)))) {
			if (istream != null) {
				return (Map<String, JsonData>) istream.readObject();
			}

		} catch (ClassNotFoundException e) {
			LOGGER.error(THE_FILE_CAN_NOT_BE_CREATE);
		} catch (FileNotFoundException e) {
			LOGGER.error(FILE_NOT_FOUND_DESERIALIZE);
		} catch (InvalidClassException e) {
			LOGGER.error(VERSION_MISMATCH);
		} catch (IOException e) {
			LOGGER.error(IO_DESERIALIZATION_ERROR);
		}
		throw new InvalidObjectException(OBJECT_NOT_RESTORED);
	}
}
