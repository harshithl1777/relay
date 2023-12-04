import { Box, Image, Heading, Text } from '@chakra-ui/react';

const QRCodeModal = (props) => {
    const { qrCodeURL } = props;

    return (
        <Box width='400px' display='flex' flexDirection='column' alignItems='center' marginTop='44px'>
            <Heading as='b'>Session QR Code</Heading>
            <Text textAlign='center' marginTop='12px' fontWeight='400' width='320px'>
                Ask your students to scan this code to be redirected to a form to fill in their details.
            </Text>
            <Image marginBottom='50px' marginTop='20px' width='300px' height='300px' src={qrCodeURL} />
        </Box>
    );
};

export default QRCodeModal;
