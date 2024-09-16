package com.Graduation_Be.mapper;

import com.Graduation_Be.dto.respone.ApprovalRequestResponseDto;
import com.Graduation_Be.model.ApprovalRequestEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ApprovalRequestMapper {

    List<ApprovalRequestResponseDto> To_Approval_Response(List<ApprovalRequestEntity> entities);

    List<ApprovalRequestResponseDto> To_Approval_Entity(List<ApprovalRequestEntity> entities);

}
