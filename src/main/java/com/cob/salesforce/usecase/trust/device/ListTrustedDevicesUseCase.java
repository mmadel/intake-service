package com.cob.salesforce.usecase.trust.device;

import com.cob.salesforce.entity.admin.trust.device.TrustedDevice;
import com.cob.salesforce.exception.business.TrustDeviceTokenException;
import com.cob.salesforce.models.admin.trust.device.TrustDeviceDTO;
import com.cob.salesforce.repositories.admin.trust.device.TrustedDeviceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ListTrustedDevicesUseCase {
    @Autowired
    TrustedDeviceRepository trustedDeviceRepository;
    @Autowired
    ModelMapper mapper;

    public List<TrustDeviceDTO> list(Integer clinicId) throws TrustDeviceTokenException {
        Optional<List<TrustedDevice>> trustedDevices = trustedDeviceRepository.findByClinicId(clinicId);
        if (!trustedDevices.get().isEmpty())
            return trustedDevices.get().stream()
                    .map(trustedDevice -> {
                        TrustDeviceDTO dto = mapper.map(trustedDevice, TrustDeviceDTO.class);
                        dto.setIsTrust(true);
                        return dto;
                    }).collect(Collectors.toList());
        else
            throw new TrustDeviceTokenException(HttpStatus.CONFLICT, TrustDeviceTokenException.NO_TRUST_DEVICES, new Object[]{clinicId.toString()});
    }
}
