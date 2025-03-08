package com.blog.global.common.auth.aop;

import com.blog.global.common.auth.MemberContext;
import com.blog.global.common.auth.MemberExtractor;
import com.blog.global.common.auth.annotations.UseGuards;
import com.blog.global.common.auth.guards.Guard;
import com.blog.global.common.dto.ResponseDto;
import com.blog.global.common.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class UseGuardsAspect {

  private final ApplicationContext applicationContext;
  private final MemberExtractor memberExtractor;

  @Around("@annotation(useGuards)")
  public Object applyGuards(ProceedingJoinPoint joinPoint, UseGuards useGuards) throws Throwable {
    try {
      this.memberExtractor.extractMemberFromToken();
    } catch (ApplicationException e) {
      return this.createErrorResponse(e);
    }

    Class<? extends Guard>[] guardClasses = useGuards.value();

    for (Class<? extends Guard> guardClass : guardClasses) {
      Guard guard = this.applicationContext.getBean(guardClass);

      try {
        guard.canActivate();
      } catch (ApplicationException e) {
        return this.createErrorResponse(e);
      }
    }

    Object proceed = joinPoint.proceed();

    MemberContext.clear();

    return proceed;
  }

  private ResponseDto<String> createErrorResponse(ApplicationException exception) {
    return ResponseDto.of(exception.getErrorCode(), exception.getMessage());
  }
}
