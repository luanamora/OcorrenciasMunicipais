using Newtonsoft.Json;
using System;
using System.Collections.Generic;

namespace WebApi.Models
{
    public partial class Ocorrencia
    {
        public Ocorrencia()
        {
            HistoricoOcorrencia = new HashSet<HistoricoOcorrencia>();
            ImagemOcorrencia = new HashSet<ImagemOcorrencia>();
        }


        [JsonProperty("cd_ocorrencia")]
        public int CdOcorrencia { get; set; }
        [JsonProperty("cd_usuario")]
        public int CdUsuario { get; set; }
        [JsonProperty("cd_prioridade")]
        public int CdPrioridade { get; set; }
        [JsonProperty("cd_tipoocorrencia")]
        public int CdTipoocorrencia { get; set; }
        [JsonProperty("cd_areaatendimento")]
        public int CdAreaatendimento { get; set; }
        [JsonProperty("cd_endereco")]
        public int CdEndereco { get; set; }
        [JsonProperty("cd_estadoocorrencia")]
        public int CdEstadoocorrencia { get; set; }
        [JsonProperty("nr_ocorrencia")]
        public int NrOcorrencia { get; set; }
        [JsonProperty("ds_mensagem")]
        public string DsMensagem { get; set; }
        [JsonProperty("ds_observacao")]
        public string DsObservacao { get; set; }
        [JsonProperty("nr_status")]
        public int NrStatus { get; set; }
        [JsonProperty("ds_finalizado")]
        public bool DsFinalizado { get; set; }
        [JsonProperty("nr_curtir")]
        public int? NrCurtir { get; set; }
        [JsonProperty("dt_cadastro")]
        public DateTime DtCadastro { get; set; }
        [JsonProperty("dt_atualizacao")]
        public DateTime? DtAtualizacao { get; set; }


        [JsonIgnore]
        public AreaAtendimento CdAreaatendimentoNavigation { get; set; }
        [JsonIgnore]
        public Endereco CdEnderecoNavigation { get; set; }
        [JsonIgnore]
        public EstadoOcorrencia CdEstadoocorrenciaNavigation { get; set; }
        [JsonIgnore]
        public PrioridadeOcorrencia CdPrioridadeNavigation { get; set; }
        [JsonIgnore]
        public TipoOcorrencia CdTipoocorrenciaNavigation { get; set; }
        [JsonIgnore]
        public Usuario CdUsuarioNavigation { get; set; }
        [JsonIgnore]
        public ICollection<HistoricoOcorrencia> HistoricoOcorrencia { get; set; }
        [JsonIgnore]
        public ICollection<ImagemOcorrencia> ImagemOcorrencia { get; set; }
    }
}
