package com.example.demo.annotation.validation.constraints;

import com.example.demo.constant.RegexpEnum;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.springframework.web.servlet.DispatcherServlet;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.regex.Pattern;

/**
 * description: 自定义的参数注解， 邮箱验证
 *
 * @auth guoshuai
 * @since 2018/8/13
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.PARAMETER})
//@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
// 被哪个类验证
@Constraint(validatedBy = {MyRegexp.MyRegexpValidator.class})
public @interface MyRegexp {

    //默认错误消息
    String message() default "格式不正确";

    //分组
    Class<?>[] groups() default {};

    //负载
    Class<? extends Payload>[] payload() default {};

    RegexpEnum value();

    class MyRegexpValidator implements ConstraintValidator<MyRegexp, String>{
        @Override
        public boolean isValid(String s, ConstraintValidatorContext context) {
            if (StringUtils.isNotEmpty(s)) {
                if (context instanceof ConstraintValidatorContextImpl) {
                    Annotation annotation = ((ConstraintValidatorContextImpl) context).getConstraintDescriptor().getAnnotation();
                    if (annotation instanceof MyRegexp) {
                        if (!Pattern.matches(((MyRegexp) annotation).value().getReg(), s)) {
                            //禁用默认提示信息
                            context.disableDefaultConstraintViolation();
                            //设置提示语
                            context.buildConstraintViolationWithTemplate(((MyRegexp) annotation).value().getComment()).addConstraintViolation();
                            return false;
                        }
                    }
                }
            }
            return true;
        }

        @Override
        public void initialize(MyRegexp myRegexp) {
        }
    }

}
