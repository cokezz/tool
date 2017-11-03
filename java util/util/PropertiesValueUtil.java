import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class IndexConf {
	//property�����ļ�
	private static final String BUNDLE_NAME = "indexConfig"; //$NON-NLS-1$
	private static final Logger log = Logger.getLogger(IndexConf.class);
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private IndexConf() {
	}

	public static String getString(String key) {
		try {
			String messager = RESOURCE_BUNDLE.getString(key);
			return messager;
		} catch (MissingResourceException e) {
			String messager = "�����������ļ�" + BUNDLE_NAME + "�з��ֲ�����" + '!' + key
					+ '!';
			log.error(messager);
			throw new RuntimeException(messager);
		}
	}
}