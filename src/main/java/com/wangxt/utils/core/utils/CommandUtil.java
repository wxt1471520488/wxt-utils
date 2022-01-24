package com.wangxt.utils.core.utils;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author wangxt
 * @description 执行command命令帮助类
 * @date 2022/1/24 10:59
 **/
public class CommandUtil {

	@Getter
	@Setter
	public static class ExecResult {
		private int code;
		private String execResult;
		private String execError;
	}

	public static ExecResult execCmd(String[] commandStr) throws Exception {
		return execCmd(commandStr, null);
	}

	public static ExecResult execCmd(String[] commandStr, String[] envp) throws Exception {
		ExecResult execResultBean = null;
		Process p = null;
		InputStream inputStream = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		InputStream inputStreamError = null;
		InputStreamReader isrError = null;
		BufferedReader brError = null;
		try {
			p = Runtime.getRuntime().exec(commandStr, envp);
			int code = p.waitFor();
			inputStream = p.getInputStream();
			isr = new InputStreamReader(inputStream);
			br = new BufferedReader(isr);
			String line = null;
			StringBuilder sbResult = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sbResult.append(line + "\n");
			}

			line = null;
			inputStreamError = p.getErrorStream();
			isrError = new InputStreamReader(inputStreamError);
			brError = new BufferedReader(isrError);
			StringBuilder sbError = new StringBuilder();
			while ((line = brError.readLine()) != null) {
				sbError.append(line + "\n");
			}
			execResultBean = new ExecResult();
			execResultBean.setCode(code);
			execResultBean.setExecResult(sbResult.toString());
			execResultBean.setExecError(sbError.toString());

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (isr != null) {
				try {
					isr.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (p != null) {
				try {
					p.destroy();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return execResultBean;
	}
}