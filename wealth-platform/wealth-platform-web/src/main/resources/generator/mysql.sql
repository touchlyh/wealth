###通用的用户角色权限表
CREATE TABLE `wealth_user_profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `nick` varchar(128)  COMMENT '用户昵称',
  `salt` varchar(64)  COMMENT '密码盐',
  `passwd` varchar(256)  COMMENT '密码MD5',
  `description` varchar(256)  COMMENT '用户描述',
  `phone` varchar(64)  COMMENT '手机号码',
  `avatar` varchar(256)  COMMENT '个人图片',
  `source` varchar(64)  COMMENT '用户来源：phone,weixin,qq,weibo',
  `third_id` varchar(256)  COMMENT '三方授权ID',
  `token` varchar(128)  COMMENT '登录token,唯一',
  `last_login_time` bigint(20)  COMMENT '上次登录时间',
  `status` bigint(20) NOT NULL COMMENT '业务状态',
  `is_deleted` bigint(20) NOT NULL COMMENT '是否删除-1删除0正常',
  `create_time` timestamp NOT NULL DEFAULT '2000-01-01 00:00:00',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `IDX_USER_TOKEN` (`token`),
  KEY `IDX_USER_THIRDID` (`third_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='wealth用户表';

CREATE TABLE `wealth_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role` varchar(64)  COMMENT '角色名称',
  `icon` varchar(256)  COMMENT '角色图标',
  `description` varchar(256)  COMMENT '角色描述',
  `duration` bigint(20)  COMMENT '角色持续时间',
  `status` bigint(20) NOT NULL COMMENT '业务状态',
  `is_deleted` bigint(20) NOT NULL COMMENT '是否删除-1删除0正常',
  `create_time` timestamp NOT NULL DEFAULT '2000-01-01 00:00:00',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='wealth角色表';

CREATE TABLE `wealth_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20)  COMMENT '用户ID',
  `role_id` bigint(20)  COMMENT '角色Id',
  `duration` bigint(20)  COMMENT '角色持续时间',
  `obtain_time` bigint(20)  COMMENT '获取角色的时间戳',
  `status` bigint(20) NOT NULL COMMENT '业务状态',
  `is_deleted` bigint(20) NOT NULL COMMENT '是否删除-1删除0正常',
  `create_time` timestamp NOT NULL DEFAULT '2000-01-01 00:00:00',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `IDX_USER_ID` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='wealth用户角色表';

CREATE TABLE `wealth_role_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint(20)  COMMENT '角色ID',
  `op` varchar(64)  COMMENT '权限:READ,WRITE,EXE',
  `res_type` varchar(64)  COMMENT '资源类型:URI',
  `parent_id` bigint(20)  COMMENT '资源上级ID',
  `res_id` bigint(20)  COMMENT '资源ID',
  `status` bigint(20) NOT NULL COMMENT '业务状态',
  `is_deleted` bigint(20) NOT NULL COMMENT '是否删除-1删除0正常',
  `create_time` timestamp NOT NULL DEFAULT '2000-01-01 00:00:00',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `IDX_ROLE_ID` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='wealth角色权限资源表';

###商品表,可以用作虚拟套餐
CREATE TABLE `wealth_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(128)  COMMENT '商品名称',
  `image` varchar(256)  COMMENT '商品图片',
  `description` varchar(512)  COMMENT '商品描述',
  `price` bigint(20)  COMMENT '商品价格:单位分',
  `linked_role` bigint(20)  COMMENT '关联的role:支付后开通的角色',
  `status` bigint(20) NOT NULL COMMENT '业务状态',
  `is_deleted` bigint(20) NOT NULL COMMENT '是否删除-1删除0正常',
  `create_time` timestamp NOT NULL DEFAULT '2000-01-01 00:00:00',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='wealth商品表';

###交易相关的表
CREATE TABLE `wealth_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20)  COMMENT '用户ID',
  `user_name` varchar(64)  COMMENT '用户名',
  `item_id` bigint(20)  COMMENT '商品ID',
  `item_name` varchar(128)  COMMENT '商品名称',
  `price` bigint(20)  COMMENT '商品价格:单位分',
  `pay_price` bigint(20)  COMMENT '支付价格:单位分',
  `linked_role` bigint(20)  COMMENT '关联的role:支付后开通的角色',
  `pay_order_sn` varchar(256)  COMMENT '支付流水号',
  `pay_method` varchar(64)  COMMENT '支付方法',
  `create_timestamp` bigint(20)  COMMENT '订单创建时间',
  `pay_timestamp` bigint(20)  COMMENT '订单支付时间',
  `status` bigint(20) NOT NULL COMMENT '业务状态:0-订单创建 2-支付成功 200-订单完成； -1订单取消 -2支付失败',
  `is_deleted` bigint(20) NOT NULL COMMENT '是否删除-1删除0正常',
  `create_time` timestamp NOT NULL DEFAULT '2000-01-01 00:00:00',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `IDX_USER_ID` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='wealth订单表';

###内容表资源存储
CREATE TABLE `wealth_novel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `novel_name` varchar(64)  COMMENT '小说名',
  `author` varchar(64)  COMMENT '作者名',
  `tags` varchar(128)  COMMENT '标签名，用分号分割',
  `cover` varchar(256)  COMMENT '封面URL',
  `summary` varchar(512)  COMMENT '摘要',
  `status` bigint(20) NOT NULL COMMENT '业务状态0正常1连载中2完结',
  `is_deleted` bigint(20) NOT NULL COMMENT '是否删除-1删除0正常',
  `create_time` timestamp NOT NULL DEFAULT '2000-01-01 00:00:00',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `IDX_NOVEL_NAME` (`novel_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='wealth小说表';

CREATE TABLE `wealth_novel_chapter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `novel_id` bigint(20)  COMMENT '小说ID',
  `chapter_name` varchar(64)  COMMENT '章节名',
  `chapter_tag` varchar(64)  COMMENT '章节标签:新',
  `sort` bigint(20) NOT NULL COMMENT '章节排序',
  `status` bigint(20) NOT NULL COMMENT '业务状态',
  `is_deleted` bigint(20) NOT NULL COMMENT '是否删除-1删除0正常',
  `create_time` timestamp NOT NULL DEFAULT '2000-01-01 00:00:00',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `IDX_NOVEL_ID` (`novel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='wealth小说章节表';

CREATE TABLE `wealth_novel_chapter_media` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `novel_id` bigint(20)  COMMENT '小说ID',
  `chapter_id` bigint(20)  COMMENT '章节ID',
  `clip` varchar(256)  COMMENT '片段',
  `sort` bigint(20) NOT NULL COMMENT '片段排序',
  `status` bigint(20) NOT NULL COMMENT '业务状态',
  `is_deleted` bigint(20) NOT NULL COMMENT '是否删除-1删除0正常',
  `create_time` timestamp NOT NULL DEFAULT '2000-01-01 00:00:00',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `IDX_NOVEL_CHAPTER_ID` (`novel_id`,`chapter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='wealth章节内容表';