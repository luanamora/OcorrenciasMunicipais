using Newtonsoft.Json;
using System;
using System.Collections.Generic;

namespace WebApi.Models
{
    public partial class HistoricoOcorrencia
    {
        [JsonProperty("cd_historicoocorrencia")]
        [JsonIgnore]    
        public int CdHistoricoocorrencia { get; set; }
        [JsonProperty("cd_ocorrencia")]
        public int CdOcorrencia { get; set; }
        [JsonProperty("ds_historicoocorrencia")]
        public string DsHistoricoocorrencia { get; set; }
        [JsonProperty("dt_cadastro")]
        public DateTime DtCadastro { get; set; }

        [JsonIgnore]
        public Ocorrencia CdOcorrenciaNavigation { get; set; }
    }
}
