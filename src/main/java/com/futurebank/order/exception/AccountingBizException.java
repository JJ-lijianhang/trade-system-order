/*
 * ====================================================================
 * 龙果学院： www.roncoo.com （微信公众号：RonCoo_com）
 * 超级教程系列：《微服务架构的分布式事务解决方案》视频教程
 * 讲师：吴水成（水到渠成），840765167@qq.com
 * 课程地址：http://www.roncoo.com/course/view/7ae3d7eddc4742f78b0548aa8bd9ccdb
 * ====================================================================
 */
package com.futurebank.order.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 账务服务业务异常类,异常代码8位数字组成,前4位固定1001打头,后4位自定义
 * 
 * @author Along
 * 
 */
public class AccountingBizException extends BizException {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(AccountingBizException.class);

	public static final AccountingBizException ACCOUNT_NOT_EXIT = new AccountingBizException("10010001", "账户不存在");
	public static final AccountingBizException ACCOUNT_SUB_AMOUNT_OUTLIMIT = new AccountingBizException("10010002", "余额不足，减款超限");
	public static final AccountingBizException ACCOUNT_UN_FROZEN_AMOUNT_OUTLIMIT = new AccountingBizException("10010003", "解冻金额超限");
	public static final AccountingBizException ACCOUNT_FROZEN_AMOUNT_OUTLIMIT = new AccountingBizException("10010004", "冻结金额超限");
	public static final AccountingBizException ACCOUNT_TYPE_IS_NULL = new AccountingBizException("10010005", "账户类型不能为空");





	public static final AccountingBizException ACCOUNT_FREQUENT_OPERATIONS = new AccountingBizException("20010001", "操作太频繁了~~,主人请休息一下~~");
	public static final AccountingBizException ACCOUNT_REPETITIVE_OPERATIONS= new AccountingBizException("20010002", "重复操作！");
	public static final AccountingBizException ACCOUNT_NOT_FOUND_ACCOUNTING_RULES= new AccountingBizException("20010003", "NOT FOUND ACCOUTNING RULES");
	public static final AccountingBizException ACCOUNT_RULE_PARAM_IS_ERROR= new AccountingBizException("20010004", "ACCOUNT_RULE_PARAM_IS_ERROR");




	public AccountingBizException() {
	}

	public AccountingBizException(String code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}

	public AccountingBizException(String code, String msg) {
		super(code, msg);
	}

	/**
	 * 实例化异常
	 * 
	 * @param msgFormat
	 * @param args
	 * @return
	 */
	public AccountingBizException newInstance(String msgFormat, Object... args) {
		return new AccountingBizException(this.code, msgFormat, args);
	}

	public AccountingBizException print() {
		logger.info("==>BizException, code:" + this.code + ", msg:" + this.msg);
		return this;
	}
}
