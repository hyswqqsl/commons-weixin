package org.flysic.commons.weixin.passive.response;

import java.io.Writer;

import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * XML解析器,转换属性加上CDATA[[]]
 * @author 雪庭
 * @sine 1.0 at 2015年5月7日
 */
public class CdataXppDriver extends XppDriver {

	@Override
	public HierarchicalStreamWriter createWriter(Writer out) {
		return new CdataPrintWriter(out);
	}

	/**
	 * 自定义输出类
	 * @author 雪庭
	 */
	class CdataPrintWriter extends PrettyPrintWriter {

		public CdataPrintWriter(Writer writer) {
			super(writer);
		}

		@SuppressWarnings("rawtypes")
		@Override
		public void startNode(String name, Class clazz) {
			super.startNode(name, clazz);
		}

		@Override
		protected void writeText(QuickWriter writer, String text) {
			writer.write("<![CDATA[");
			writer.write(text);
			writer.write("]]>");
		}
	}

}
