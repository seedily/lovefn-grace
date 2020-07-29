# mybatis-generator-mysql
mysql plugin for mybatis-generator. mvn prj.

## 主要功能：

### BatchInsertPlugin:
批量插入

### LombokRemarksPlugin:
添加lombok注解和表、字段相关的注释。

### PaginationPlugin:
生成select的固定limit语句，同时在example增加limit功能。

### SelectForUpdatePlugin:
生成select的固定selectForUpdate语句，可以在example中设置wait 的时间。
***使用时千万注意，对不根据主键查询会加表级锁，切记加上主键***


## 使用方法：
###1.加入此工程的dependencies到maven的plugin element。
    <plugins>			
    	 <plugin>				
    		  <groupId>org.mybatis.generator</groupId>				
    		  <artifactId>mybatis-generator-maven-plugin</artifactId>					  
    		  <version>1.3.2</version>				
    		  <configuration>					
    			   <verbose>true</verbose>					
    			   <overwrite>true</overwrite>				
    		  </configuration>				
    		  <dependencies>					
    			   <dependency>							
                        <groupId>xxxx</groupId>
                        <artifactId>grace-starter-mybatis</artifactId>				
    					<version>0.0.2-SNAPSHOT</version>					
    			   </dependency>				
    		  </dependencies>			
    	 </plugin>		
    </plugins>
    
###2.编辑generatorConfig.xml
    <generatorConfiguration>
         ...
          <context id="mysql" targetRuntime="MyBatis3">
              ...
               <plugin type="com.…….LombokRemarksPlugin" />
               <plugin type="com.…….PaginationPlugin" />
               <plugin type="com.…….SelectForUpdatePlugin" />
               <plugin type="com.…….BatchInsertPlugin" />
         </context>
    </generatorConfiguration>
###3. 执行指令：mvn mybatis-generator:generate

###4. 自定义数据库方法的方案：
    如需对生成的*Mapper添加其他自定义的方法，如联表查询，可以通过添加父类方式实现，方案如下：
    1. 命名格式遵循*ExtMapper，并做好rootInterface配置（示例如下property标签）
    2. 需要手工添加配套的*ExtMapper.xml（namespace指向*ExtMapper），建议拷贝原生*Mapper.xml再编辑
    注意：切忌在原生*Mapper上编辑，以免因为覆盖而丢失
    上述方案参考：https://blog.csdn.net/weixin_30648587/article/details/99412424
