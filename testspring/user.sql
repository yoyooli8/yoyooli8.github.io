CREATE TABLE `User` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_name` varchar(500)   NOT NULL COMMENT '用户名称',
  `real_name` varchar(500)   DEFAULT NULL COMMENT '真实用户名称',
  `address`  varchar(1000)  DEFAULT NULL COMMENT '住址',
  `age`      int(3)         DEFAULT NULL COMMENT '年龄',
  `sex`      int(1)         DEFAULT NULL COMMENT '姓别',
  `phone`    varchar(20)    DEFAULT NULL COMMENT '姓别',
  `userpwd`  varchar(100)   DEFAULT NULL COMMENT '密码',
  `salt`     varchar(100)   DEFAULT NULL COMMENT '加密盐值',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` int(11) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `userName` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户信息';